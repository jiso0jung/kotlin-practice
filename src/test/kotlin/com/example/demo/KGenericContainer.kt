package com.example.demo

import org.testcontainers.containers.GenericContainer

open class KGenericContainer(imageName: String) : GenericContainer<KGenericContainer>(imageName)
