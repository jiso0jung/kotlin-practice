package com.example.demo.repository

import com.example.demo.domain.Members
import org.springframework.data.repository.CrudRepository

interface MembersRepository : CrudRepository<Members, String>
