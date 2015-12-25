package businesslogic.listbl;

import businesslogic.utilitybl.Helper;
import po.OperationLogPO;
import po.PaymentPO;
import vo.ListVO;
import vo.PaymentVO;
import vo.VO2PO;

import java.rmi.RemoteException;

/**
 * Created by Administrator on 2015/11/16 0016.
 *
 * @author myk
 */
public class Payment extends List {
    /**
     * Instantiates a new Payment.
     *
     * @throws RemoteException the remote exception
     */
    public Payment() throws RemoteException {
        super();
    }

    public boolean save(ListVO listVO) throws RemoteException {
        PaymentVO vo = null;
        vo = (PaymentVO) listVO;

        PaymentPO po = VO2PO.convert(vo);

        inquiryDataService.saveOperationLog(new OperationLogPO(Helper.getTime(),Helper.getUserType().toString(),
                "保存付款单"));
        return listDataService.save(po);

    }


}
