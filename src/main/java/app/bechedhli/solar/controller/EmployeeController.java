package app.bechedhli.solar.controller;

import app.bechedhli.solar.entity.User;
import app.bechedhli.solar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final UserRepository userRepository;

    @Autowired
    public EmployeeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found: " + id));
    }

    @PostMapping
    public User create(@RequestBody User user) {
        if (user.getNom() == null) user.setNom(user.getName());
        if (user.getPrenom() == null) user.setPrenom("");
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userRepository.findById(id)
                .map(existing -> {
                    if (user.getName() != null) existing.setName(user.getName());
                    if (user.getRole() != null) existing.setRole(user.getRole());
                    if (user.getDept() != null) existing.setDept(user.getDept());
                    if (user.getStatus() != null) existing.setStatus(user.getStatus());
                    if (user.getPhone() != null) existing.setPhone(user.getPhone());
                    if (user.getEmail() != null) existing.setEmail(user.getEmail());
                    if (user.getSalary() != null) existing.setSalary(user.getSalary());
                    if (user.getJoinDate() != null) existing.setJoinDate(user.getJoinDate());
                    return userRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}