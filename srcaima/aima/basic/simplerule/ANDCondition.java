package aima.basic.simplerule;

import aima.basic.ObjectWithDynamicAttributes;

/**
 * Implementation of an AND condition.
 *
 */

/**
 * @author Ciaran O'Reilly
 * 
 */
public class ANDCondition extends Condition {
	private Condition left;

	private Condition right;

	public ANDCondition(Condition aLeftCon, Condition aRightCon) {
		assert (null != aLeftCon);
		assert (null != aRightCon);

		left = aLeftCon;
		right = aRightCon;
	}

	@Override
	public boolean evaluate(ObjectWithDynamicAttributes p) {
		return (left.evaluate(p) && right.evaluate(p));
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		return sb.append("[").append(left).append(" && ").append(right).append(
				"]").toString();
	}
}
