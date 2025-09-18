package com.rockthejvm

import scala.util.{Failure, Success, Try}
import java.net.URL
import java.io.InputStream

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

    //Ex2: Once we have a java.net.URL object, we can attempt to connect to it and extract its contents. The methods
    // URL.openConnection() and Connection.getInputStream() are used to achieve this. Write a function/method that accepts
    // a String that should represent a URL, and returns a Try[java.io.InputStream], which will either contain an
    // InputStream from which we can read the URL contents or an exception that details why we cannot.
    //This method will result in multiple nestings of Try types, so you may wish to examine flatMap as a way of avoiding
    // the complexity this would bring.
    //
    //An alternative is to access a website. To do this, you need to call the openConnection method with an argument
    // representing the proxy server that is used to cross the firewall. This is done as
    //val proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress ("wwproxy", 8080))|
    //...openConnection(proxy)...
    // Clue 2: With the URL object from step 1 wrapped in try, use flatmap to obtain the URL object and open connection
    // and getInputStream from it, have a if else statement to see if we want to use proxy or not.


    def readUrl(url: String, useProxy: Boolean):Try[InputStream] = {
        val urlObj = parseUrl(url)
        urlObj.flatMap(url=> Try(url.openConnection()))
            .flatMap(connection=> Try(connection.getInputStream))
    }
    //At each step, exceptions are caught inside Try. The whole chain is resilient — if any step throws, the result is a Failure.

    def readUrl2(url: String, useProxy: Boolean): Try[InputStream] = {
        val urlObj = parseUrl(url).map(url => url.openConnection())
            .map(connection => connection.getInputStream)
        urlObj
    }
    //Here’s the problem:
    //map just applies the function directly.
    //If url.openConnection() or connection.getInputStream throws, the exception escapes the map instead of being wrapped in a Try.
    //So effectively: readUrl2 only catches errors from parseUrl.
    //Failures inside openConnection() or getInputStream are not converted to Failure, they’ll just blow up your program with an exception.
}
