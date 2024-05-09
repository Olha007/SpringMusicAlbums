package ua.edu.znu.musicalbums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.znu.musicalbums.model.Group;
import ua.edu.znu.musicalbums.repository.GroupRepository;

@Controller
public class GroupsController {
    @Autowired
    private GroupRepository groupRepository;

//    @GetMapping("/groups")
//    public String groups(Model model) {
//        Iterable<Group> groups = groupRepository.findAll();
//        model.addAttribute("groups", groups);
//        return "groups";
//    }

    @GetMapping("/groups")
    public String groups(@RequestParam(required = false) String search, Model model) {
        Iterable<Group> groups;
        if (search != null && !search.isEmpty()) {
            groups = groupRepository.findByGroupNameContainingIgnoreCase(search);
        } else {
            groups = groupRepository.findAll();
        }
        model.addAttribute("groups", groups);
        return "groups";
    }

    @PostMapping("/groupEditForm")
    public String groupEditForm(@RequestParam Long groupId, Model model) {
        Group group = groupRepository.findById(groupId).orElse(new Group());
        model.addAttribute("group", group);
        return "groupedit";
    }

    @PostMapping("/groupEdit")
    public String groupEdit(@RequestParam Long groupId, @RequestParam String groupName, Model model) {
        Group group = groupRepository.findById(groupId).orElse(new Group());
        group.setGroupName(groupName);
        groupRepository.save(group);
        return "redirect:/groups";
    }

    @PostMapping("/groupRemove")
    public String groupRemove(@RequestParam Long groupId) {
        groupRepository.findById(groupId).ifPresent(groupRepository::delete);
        return "redirect:/groups";
    }

    @GetMapping("/groupAddForm")
    public String groupAddForm() {
        return "groupadd";
    }

    @PostMapping("/groupAdd")
    public String groupAdd(@RequestParam String groupName) {
        Group group = new Group();
        group.setGroupName(groupName);
        groupRepository.save(group);
        return "redirect:/groups";
    }
}
