package codemining.languagetools;

import java.io.Serializable;
import java.util.List;
import java.util.SortedMap;

import org.apache.commons.io.filefilter.AbstractFileFilter;

import com.google.common.base.Objects;

/**
 * Interface of a code tokenizer.
 * 
 * @author Miltos Allamanis <m.allamanis@ed.ac.uk>
 * 
 */
public interface ITokenizer extends Serializable {

	public static class FullToken {
		public final String token;

		public final String tokenType;

		public FullToken(final FullToken other) {
			token = other.token;
			tokenType = other.tokenType;
		}

		public FullToken(final String tokName, final String tokType) {
			token = tokName;
			tokenType = tokType;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof FullToken))
				return false;
			final FullToken other = (FullToken) obj;
			return other.token.equals(token)
					&& other.tokenType.equals(tokenType);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(token, tokenType);
		}

		@Override
		public String toString() {
			return token + " (" + tokenType + ")";
		}

	}

	/**
	 * A sentence end (constant) token
	 */
	static final String SENTENCE_END = "<SENTENCE_END/>";

	/**
	 * A sentence start (constant) token
	 */
	static final String SENTENCE_START = "<SENTENCE_START>";

	/**
	 * Return a list with the full tokens.
	 * 
	 * @param code
	 * @return
	 */
	SortedMap<Integer, FullToken> fullTokenListWithPos(final char[] code);

	/**
	 * Return a file filter, filtering the files that can be tokenized.
	 * 
	 * @return
	 * 
	 */
	AbstractFileFilter getFileFilter();

	/**
	 * Return the token type that signifies that a token is an identifier.
	 * 
	 * @return
	 */
	String getIdentifierType();

	/**
	 * Return a full token given a string token.
	 * 
	 * @param token
	 * @return
	 */
	FullToken getTokenFromString(final String token);

	/**
	 * Get the list of tokens from the code.
	 * 
	 * @param code
	 * @return
	 */
	List<FullToken> getTokenListFromCode(final char[] code);

	/**
	 * Tokenize some code.
	 * 
	 * @param code
	 *            the code
	 * @return a list of tokens
	 */
	List<String> tokenListFromCode(final char[] code);

	/**
	 * Return a list of tokens along with their positions.
	 * 
	 * @param code
	 * @return
	 */
	SortedMap<Integer, String> tokenListWithPos(final char[] code);

}