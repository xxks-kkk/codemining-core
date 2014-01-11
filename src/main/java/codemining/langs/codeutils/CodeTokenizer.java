/**
 * 
 */
package codemining.langs.codeutils;

import java.util.List;
import java.util.SortedMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.threecrickets.jygments.ResolutionException;
import com.threecrickets.jygments.grammar.Token;
import com.threecrickets.jygments.grammar.TokenType;

/**
 * Tokenize the code using the real tokens.
 * 
 * @author Miltos Allamanis <m.allamanis@ed.ac.uk>
 * 
 */
public class CodeTokenizer extends AbstractJygmentsTokenizer {

	private static final long serialVersionUID = -981980819807626795L;

	/**
	 * @param fileSuffix
	 * @throws ResolutionException
	 */
	public CodeTokenizer(String fileSuffix) throws ResolutionException {
		super(fileSuffix);
	}

	@Override
	public SortedMap<Integer, FullToken> fullTokenListWithPos(char[] code) {
		final Iterable<Token> tokens = lexer.getTokens(new String(code));
		final SortedMap<Integer, FullToken> tokensWithPos = Maps.newTreeMap();
		tokensWithPos.put(-1, new FullToken(SENTENCE_START, SENTENCE_START));
		tokensWithPos.put(Integer.MAX_VALUE, new FullToken(SENTENCE_END,
				SENTENCE_END));
		for (final Token tok : tokens) {
			if (isProgramToken(tok)) {
				continue;
			}
			tokensWithPos.put(tok.getPos(), new FullToken(getTokenString(tok),
					tok.getType().getName()));
		}
		return tokensWithPos;
	}

	@Override
	public String getIdentifierType() {
		return TokenType.Name.getName();
	}

	@Override
	public FullToken getTokenFromString(String token) {
		return getTokenListFromCode(token.toCharArray()).get(1);
	}

	@Override
	public List<FullToken> getTokenListFromCode(char[] code) {
		final Iterable<Token> tokens = lexer.getTokens(new String(code));
		final List<FullToken> toks = Lists.newArrayList();
		toks.add(new FullToken(SENTENCE_START, SENTENCE_START));
		for (final Token tok : tokens) {
			if (isProgramToken(tok)) {
				continue;
			}
			toks.add(new FullToken(getTokenString(tok), tok.getType().getName()));
		}
		toks.add(new FullToken(SENTENCE_END, SENTENCE_END));
		return toks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see codemining.langs.codeutils.AbstractCodeTokenizer#getTokenString(com.
	 * threecrickets.jygments.grammar.Token)
	 */
	@Override
	public String getTokenString(final Token tok) {
		return tok.getValue().trim();
	}

}