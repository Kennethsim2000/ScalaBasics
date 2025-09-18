package com.rockthejvm

import scala.util.{Failure, Success, Try}
import java.net.URL

object ScalaPractice8 extends App {
    //Ex1. The class java.net.URL is used to represent an endpoint of communication that is identified by a URL.
    // Write a Scala function/method, parseURL, that takes a String representing a URL object and attempts to construct
    // a java.net.URL object from it. If the constructor for java.net.URL cannot recognise a valid URL syntax, it throws
    // the java.net.MalformedURLException. So, the parseURL method should return a Try[URL] that encapsulates both cases
    //Ensure that your method behaves as expected.
    //Clue: Wrap a URL object with a try

    def parseUrl(url: String) = {
        Try(URL(url))
    }
    parseUrl("https://www.google.com") match {
        case Success(url) => println(s"url $url is a valid url")
        case Failure(exception) => println(s"Exception encountered: $exception")
    }

}
