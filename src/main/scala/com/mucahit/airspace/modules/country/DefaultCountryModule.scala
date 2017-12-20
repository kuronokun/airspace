package com.mucahit.airspace.modules.country

import com.mucahit.airspace.core.namespaces.module.CountryModule
import com.mucahit.airspace.dao.country.DefaultCountryDAO
import com.mucahit.airspace.services.country.DefaultCountryService

object DefaultCountryModule extends CountryModule with DefaultCountryDAO with DefaultCountryService