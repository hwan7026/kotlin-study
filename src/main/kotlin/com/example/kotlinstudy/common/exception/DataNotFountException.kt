package com.example.kotlinstudy.common.exception

class DataNotFountException : NullPointerException {
    constructor() : super()
    constructor(s: String?) : super(s)
}