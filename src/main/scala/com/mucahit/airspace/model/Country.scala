package com.mucahit.airspace.model

case class Country(id: Long,
                   code: String,
                   name: String,
                   continent: String,
                   wikipedia_link: String,
                   keywords: Set[String],
                  )
