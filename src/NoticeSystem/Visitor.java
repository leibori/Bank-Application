package NoticeSystem;

import WithdrawDeposit.NoTransFee;
import WithdrawDeposit.OverDrawn;
import WithdrawDeposit.TransFee;

public interface Visitor {
    public void visit(NoTransFee ntf);
    public void visit(OverDrawn od);
    public void visit(TransFee tf);



}
