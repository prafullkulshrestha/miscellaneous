package com.es.upload;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ESHttpAdapter adapter = new ESHttpAdapter();
        adapter.createData();
    }
}
