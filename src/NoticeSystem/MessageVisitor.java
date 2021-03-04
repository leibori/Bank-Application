package NoticeSystem;

import WithdrawDeposit.NoTransFee;
import WithdrawDeposit.OverDrawn;
import WithdrawDeposit.TransFee;

public class MessageVisitor implements Visitor{
    @Override
    public void visit(NoTransFee ntf) {
        System.out.println("PAY ATTENTION: Mail has been sand to your email address with information.");

    }

    @Override
    public void visit(OverDrawn od) {
        System.out.println("PAY ATTENTION: SMS has been sand to your phone with information about the OverDraw.");

    }

    @Override
    public void visit(TransFee tf) {
        System.out.println("PAY ATTENTION: A Letter was send to your home address with information about the TransFee.");


    }
}
