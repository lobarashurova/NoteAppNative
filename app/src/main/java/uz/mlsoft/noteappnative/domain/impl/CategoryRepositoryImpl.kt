package uz.mlsoft.noteappnative.domain.impl

import uz.mlsoft.noteappnative.data.dao.CategoryDao
import uz.mlsoft.noteappnative.data.entity.CategoryEntity
import uz.mlsoft.noteappnative.domain.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(val categoriesDao: CategoryDao) :
    CategoryRepository {
    override fun getAllCategories(): List<CategoryEntity> = categoriesDao.getAllCategories()

    override fun insertCategories(categoriesEntity: CategoryEntity) {
        categoriesDao.insertCategories(categoriesEntity)
    }

    override fun deleteCategories(categoryId: Int) {
        categoriesDao.deleteNotesByCategoryId(categoryId)
    }


    override fun updateCategories(categoriesEntity: CategoryEntity) {
        categoriesDao.insertCategories(categoriesEntity)
    }
}