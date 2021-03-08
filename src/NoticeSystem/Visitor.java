package NoticeSystem;

import WithdrawDeposit.NoTransFee;
import WithdrawDeposit.OverDrawn;
import WithdrawDeposit.TransFee;
/**
 *  The Visitor class gives us the opportunity to send to the client Message in 3 different platforms: sms,mail and letter .
 *  By using the visitor pattern we can send message in depends of the state of the bank account.
 */
public interface Visitor {
    public void visit(NoTransFee ntf);
    public void visit(OverDrawn od);
    public void visit(TransFee tf);



}
