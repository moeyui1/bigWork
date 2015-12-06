package dataservice.commoditydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.StockPO;

public interface CommodityDataService extends Remote{
	public void update(StockPO PO) throws RemoteException;
	public StockPO check() throws RemoteException;//返回完整的stockpo

	public StockPO initial() throws RemoteException;

}
