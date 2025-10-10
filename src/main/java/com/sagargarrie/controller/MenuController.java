package com.sagargarrie.controller;

import com.sagargarrie.dto.MenuItemDTO;
import com.sagargarrie.entity.MenuItem;
import com.sagargarrie.repository.MenuItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "http://localhost:5173")
public class MenuController {
    private final MenuItemRepository menuRepo;
    public MenuController(MenuItemRepository menuRepo) { this.menuRepo = menuRepo; }

    @GetMapping
    public List<MenuItemDTO> getMenu(@RequestParam(value="category", required=false) String category) {
        List<MenuItem> items = (category == null || category.isBlank())
                ? menuRepo.findAll()
                : menuRepo.findByCategoryIgnoreCase(category);
        return items.stream().map(this::map).collect(Collectors.toList());
    }

    private MenuItemDTO map(MenuItem m) {
        MenuItemDTO d = new MenuItemDTO();
        d.setId(m.getId());
        d.setCategory(m.getCategory());
        d.setName(m.getName());
        d.setDescription(m.getDescription());
        d.setPrice(m.getPrice());
        d.setVeg(m.getVeg());
        d.setImageUrl(m.getImageUrl());
        return d;
    }
}
