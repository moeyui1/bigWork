package businesslogicservice;

import util.ExistException;
import util.ResultMessage;
import vo.ConstantVO;
import vo.DriverVO;
import vo.VehicleVO;

import java.rmi.RemoteException;

/**
 * Created by Administrator on 2015/10/24 0024.
 */
public interface ManageblService {
    boolean addDriver(DriverVO driverVO) throws RemoteException, ExistException;

    ResultMessage delDriver(DriverVO drivervO) throws RemoteException;

    DriverVO checkDriver(String driveNumber) throws RemoteException;

    ResultMessage addVehicle(VehicleVO vehicleVO) throws RemoteException, ExistException;

    ResultMessage delVehicle(VehicleVO vehiclevO) throws RemoteException;

    VehicleVO checkVehicle(String vehicleNumber) throws RemoteException;

    void updateSalary(String position, String Type) throws RemoteException;

    void updateConstant(ConstantVO constantVO) throws RemoteException;
}
