package edu.moravian.BattleshipBot.exceptions;

public class InternalServerException extends Exception
{
    public InternalServerException(String message)
    {
        super("Internal Server Error: " + message);
    }
}