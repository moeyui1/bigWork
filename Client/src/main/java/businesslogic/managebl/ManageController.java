package businesslogic.managebl;

import businesslogicservice.ManageblService;
import util.ExistException;
import vo.*;

import java.rmi.RemoteException;
import java.util.Iterator;

/**
 * Created by Administrator on 2015/11/7 0007.
 *
 * @author myk
 */
public class ManageController implements ManageblService {
    /**
     * The Manage.
     */
    private Manage manage;

    /**
     * Instantiates a new Manage controller.
     *
     * @throws RemoteException the remote exception
     */
    public ManageController() throws RemoteException {
        manage = new Manage();
    }

    public boolean addDriver(DriverVO driverVO) throws RemoteException, ExistException {
        return manage.addDriver(driverVO);
    }

    public boolean delDriver(DriverVO drivervO) throws RemoteException {
        return manage.delDriver(drivervO);
    }

    /**
     * show all Driver info
     * @return
     * @throws RemoteException
     */
    public Iterator<DriverVO> checkDriver() throws RemoteException {
        return manage.checkDriver();
    }

    public DriverVO checkDriver(String driveNumber) throws RemoteException {
        return manage.checkDriver(driveNumber);
    }

    public boolean addVehicle(VehicleVO vehicleVO) throws RemoteException, ExistException {
        return manage.addVehicle(vehicleVO);
    }

    public boolean delVehicle(VehicleVO vehiclevO) throws RemoteException {
        return manage.delVehicle(vehiclevO);
    }

    /**
     * show all Vehivles info
     * @return
     * @throws RemoteException
     */
    public Iterator<VehicleVO> checkVehicle() throws RemoteException {
        return manage.checkVehicle();
    }

    public Iterator<OrganizationVO> checkOrganization() throws RemoteException {
        return manage.checkOrganization();
    }

    public boolean addOrganization(OrganizationVO vo) throws RemoteException, ExistException {
        return manage.addOrganization(vo);
    }

    public boolean delOrganization(OrganizationVO vo) throws RemoteException, ExistException {
        return manage.delOrganization(vo);
    }

    /**
     * 按照编号查找
     * @param vehicleNumber 传入车辆编号
     * @return
     * @throws RemoteException
     */
    public VehicleVO checkVehicle(String vehicleNumber) throws RemoteException {
        return manage.checkVehicle(vehicleNumber);
    }

    public void updateSalary(String position, String Type) throws RemoteException {
        manage.updateSalary(position,Type);
    }

    public void updateConstant(ConstantVO constantVO) throws RemoteException, ExistException {
        manage.updateConstant(constantVO);
    }

    public String getSalary(String position) throws RemoteException {
        return manage.getSalary(position);
    }

    public boolean addWorker(WorkerVO workerVO) throws RemoteException, ExistException {
        return manage.addWorker(workerVO);
    }

    public boolean delWorker(WorkerVO workerVO) throws RemoteException, ExistException {
        return manage.delWorker(workerVO);
    }

    public Iterator<WorkerVO> checkWorker() throws RemoteException {
        return manage.checkWorker();
    }

    public Iterator<WorkerVO> checkWorker(String name) throws RemoteException {
        return manage.checkWorker(name);
    }

    public WorkerVO checkWorkerByID(String id) {
        return manage.checkWorkerByID(id);
    }

    public boolean editWorker(WorkerVO oldWorkerVO,WorkerVO newWorkerVO) throws RemoteException, ExistException {
        return manage.editWorker(oldWorkerVO,newWorkerVO);
    }

    public Iterator<ConstantVO> checkConstant() throws RemoteException {
        return manage.checkConstant();
    }

    public boolean editDriver(DriverVO ovo, DriverVO nvo) throws RemoteException, ExistException {
        return manage.editDriver(ovo, nvo);
    }

    public boolean editVehicle(VehicleVO ovo, VehicleVO nvo) throws RemoteException, ExistException {
        return manage.editVehicle(ovo, nvo);
    }
}
