package stubs.data1;

import dataservice.managedataservice.ManageDataService;
import po.ConstantPO;
import po.DriverPO;
import po.VehiclePO;

public class ManageDataService_Stub implements ManageDataService {

    public ManageDataService_Stub() {

    }

    public boolean addDriver(DriverPO driverPO) {
        System.out.println("Success");
        return true;
    }

    public boolean deleteDriver(DriverPO po) {
        System.out.println("Success");
        return true;
    }

    public DriverPO checkDriver(String driveNumber) {
        System.out.println("Success");
        DriverPO po = new DriverPO();
        return po;
    }

    public boolean addVehicle(VehiclePO vehiclePO) {
        System.out.println("Success");
        return true;
    }

    public boolean deleteVehicle(VehiclePO po) {
        System.out.println("Success");
        return true;
    }

    public VehiclePO checkVehicle(String vehicleNumber) {
        System.out.println("Success");
        VehiclePO po = new VehiclePO();
        return po;
    }

    public void updateSalary(String position, String Type) {
        System.out.println("Success");
    }

    public void updateConstant(ConstantPO constantPO) {
        System.out.println("Success");
    }
}