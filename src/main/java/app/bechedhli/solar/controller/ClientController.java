package app.bechedhli.solar.controller;

import app.bechedhli.solar.entity.User;
import app.bechedhli.solar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final UserRepository userRepository;

    @Autowired
    public ClientController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAll() {
        return userRepository.findByUserRole(User.Role.CLIENT);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found: " + id));
    }

    @PostMapping
    public User create(@RequestBody User user) {
        user.setUserRole(User.Role.CLIENT);
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userRepository.findById(id)
                .map(existing -> {
                    if (user.getName() != null) existing.setName(user.getName());
                    if (user.getPhone() != null) existing.setPhone(user.getPhone());
                    if (user.getEmail() != null) existing.setEmail(user.getEmail());
                    return userRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Client not found: " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}