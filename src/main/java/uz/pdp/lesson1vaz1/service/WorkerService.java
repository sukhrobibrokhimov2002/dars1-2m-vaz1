package uz.pdp.lesson1vaz1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson1vaz1.entity.Address;
import uz.pdp.lesson1vaz1.entity.Department;
import uz.pdp.lesson1vaz1.entity.Worker;
import uz.pdp.lesson1vaz1.payload.ResponseApi;
import uz.pdp.lesson1vaz1.payload.WorkerDto;
import uz.pdp.lesson1vaz1.repositrory.AddressRepository;
import uz.pdp.lesson1vaz1.repositrory.DepartmentRepository;
import uz.pdp.lesson1vaz1.repositrory.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;

    public ResponseApi add(WorkerDto workerDto) {

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (!optionalDepartment.isPresent() || existsByPhoneNumber)
            return new ResponseApi("Error", false);
        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address save = addressRepository.save(address);


        Worker worker = new Worker();
        worker.setFullName(workerDto.getFullName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setDepartment(optionalDepartment.get());
        worker.setAddress(save);
        workerRepository.save(worker);
        return new ResponseApi("Successfully added", true);
    }


    public List<Worker> getAll() {
        List<Worker> all = workerRepository.findAll();
        return all;
    }

    public Worker getOneById(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) return null;
        return optionalWorker.get();
    }

    public ResponseApi delete(Integer id) {
        try {
            workerRepository.deleteById(id);
            return new ResponseApi("Successfully deleted", true);
        } catch (Exception e) {
            return new ResponseApi("Error in deleting", false);
        }

    }

    public ResponseApi edit(Integer id, WorkerDto workerDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        boolean numberAndIdNot = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (!optionalDepartment.isPresent() || !optionalWorker.isPresent() || numberAndIdNot) {
            return new ResponseApi("Error", false);
        }
        Address address = new Address();
        address.setHomeNumber(workerDto.getHomeNumber());
        address.setStreet(workerDto.getStreet());

        Worker worker = optionalWorker.get();
        worker.setDepartment(optionalDepartment.get());
        worker.setAddress(address);
        worker.setFullName(workerDto.getFullName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new ResponseApi("Successfully edited", true);

    }
}
