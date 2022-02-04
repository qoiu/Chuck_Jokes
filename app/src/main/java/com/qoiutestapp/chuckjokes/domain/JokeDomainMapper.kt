package com.qoiutestapp.chuckjokes.domain

import com.qoiutestapp.chuckjokes.Abstract

interface JokeDomainMapper<T>: Abstract.Mapper.DomainToUi<List<String>,T>