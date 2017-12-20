package com.mucahit.airspace.core.namespaces.module

import com.mucahit.airspace.core.namespaces.dao.DAO
import com.mucahit.airspace.core.namespaces.service.Service

trait Module[T] extends DAO[T] with Service[T]
