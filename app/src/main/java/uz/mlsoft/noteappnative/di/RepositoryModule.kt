package uz.mlsoft.noteappnative.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.mlsoft.noteappnative.domain.CategoryRepository
import uz.mlsoft.noteappnative.domain.NoteRepository
import uz.mlsoft.noteappnative.domain.impl.CategoryRepositoryImpl
import uz.mlsoft.noteappnative.domain.impl.NoteRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @[Binds Singleton]
    fun provideCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository


    @[Binds Singleton]
    fun provideGroupRepository(impl: NoteRepositoryImpl): NoteRepository


}