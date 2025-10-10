package com.malfaa.pmdp.mapper;

import com.malfaa.pmdp.dto.CategoryDTO;
import com.malfaa.pmdp.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    public CategoryDTO toDto(Category category){
        if (category == null){
            return null;
        }
        return new CategoryDTO(category.getId(),category.getName(), category.getDescription());
    }

    public Category toEntity(CategoryDTO dto){
        if (dto == null){
            return null;
        }

        Category ct = new Category();
        ct.setId(dto.id());
        ct.setName(dto.name());
        ct.setDescription(dto.description());
        return ct;
    }
    public List<CategoryDTO> toDtoList(List<Category> categories){
        if (categories == null){
            return null;
        }
        return categories.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
