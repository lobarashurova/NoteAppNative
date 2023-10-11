package uz.mlsoft.noteappnative.domain

import uz.mlsoft.noteappnative.data.entity.CategoryEntity

interface CategoryRepository {
    fun getAllCategories(): List<CategoryEntity>
    fun insertCategories(categoriesEntity: CategoryEntity)
    fun deleteCategories(categoryId:Int)
    fun updateCategories(categoriesEntity: CategoryEntity)
}