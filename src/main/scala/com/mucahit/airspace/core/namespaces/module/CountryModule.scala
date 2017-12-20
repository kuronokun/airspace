package com.mucahit.airspace.core.namespaces.module

import com.mucahit.airspace.core.namespaces.dao.CountryDAO
import com.mucahit.airspace.core.namespaces.service.CountryService
import com.mucahit.airspace.model.Country

trait CountryModule extends Module[Country] with CountryDAO with CountryService
