package com.shorten.api.db.repo

import com.shorten.api.db.model.LinkModel
import org.springframework.data.repository.CrudRepository

interface LinkRepo : CrudRepository<LinkModel, String>