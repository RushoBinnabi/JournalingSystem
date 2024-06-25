/**
 * Name: Rusho Binnabi
 * Date: 5/23/2024
 * Project: JournalSystem - Journal
 * Contact Information: RushoBinnabi123@yahoo.com
 */

package org.example.journal;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Journal {

    // this Journal class file has the name and the functionality of the journal files that will be used for the journal entries.

    private static File fileHandler;
    private FileWriter fileWriter;
    private final ArrayList<String> listOfFiles = new ArrayList<>();
    public static double SCREEN_WIDTH = 800.0;
    public static double SCREEN_HEIGHT = 500.0;

    /**
     * empty Journal Constructor.
     */

    public Journal() {

    }

    /**
     * this getFileHandler() method gets the file handler that handles the journal entries.
     * @return the file handler.
     */

    public static File getFileHandler() {
        return fileHandler;
    }

    /**
     * this setFileHandler() method sets the file handler for the journal entries.
     * @param fileHandler the journal entry files being set for the file handler.
     */

    public static void setFileHandler(File fileHandler) {
        Journal.fileHandler = fileHandler;
    }

    /**
     * this getFileWriter() method gets the file writer for writing the journal entries.
     * @return the file writer.
     */

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    /**
     * this setFileWriter() method sets the file writer for writing the journal entries.
     * @param fileWriter the file writer being set.
     */

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    /**
     * this getListOfFiles() method gets the arraylist of files.
     * @return the arraylist of files.
     */

    public ArrayList<String> getListOfFiles() {
        return listOfFiles;
    }

    /**
     * this createJournalEntry() method creates a journal entry.
     * @param name the name of the journal entry.
     * @param contents the contents that will be written to the journal entry.
     * @return the name of the journal entry that had contents written to it.
     */

    public String createJournalEntry(String name, String contents) {
        String errorMessage;
        try {
            setFileHandler(new File(name.concat(".txt")));
            if (contents.isEmpty() && !name.isEmpty()) {
                setFileWriter(new FileWriter(getFileHandler()));
                getFileWriter().write(contents);
                errorMessage = "Empty Journal Entry Created";
                getFileWriter().close();
            }
            else if (name.isEmpty()) {
                errorMessage = "Journal Entry Name Is Empty";
            }
            else {
                setFileWriter(new FileWriter(getFileHandler()));
                getFileWriter().write(contents);
                errorMessage = "Journal Entry Created";
                getFileWriter().close();
            }
        }
        catch (Exception e) {
            return "Error. The journal entry could not be created.";
        }
        return errorMessage;
    }

    /**
     * this readJournalEntry() method reads a journal entry.
     * @param name the name of the journal entry.
     * @return what was read from the journal entry.
     */

    public String readJournalEntry(String name) {
        String test;
        String errorMessage;
        try {
            test = new String(Files.readAllBytes(Paths.get(name.concat(".txt"))));
        }
        catch (Exception e) {
            errorMessage = "Error. The journal entry could not be read.";
            return errorMessage;
        }
        return test;
    }

    /**
     * this viewJournalEntries() method views the journal entries.
     * @return the list of journal entries.
     */

    public ArrayList<String> viewJournalEntries() {
        setFileHandler(new File(System.getProperty("user.dir")));
        File[] files = getFileHandler().listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".txt")) {
                    getListOfFiles().add(file.getName() + "\n");
                }
            }
        }
        return getListOfFiles();
    }

    /**
     * this deleteJournalEntry() method deletes a journal entry.
     * @param name the name of the file to be deleted.
     * @return the name of the file that was deleted.
     */

    public String deleteJournalEntry(String name) {
        String errorMessage;
        if (name.isEmpty()) {
            errorMessage = "Error. The journal entry could not be deleted because it doesn't exist";
            return errorMessage;
        }
        else {
            try {
                setFileHandler(new File(name.concat(".txt")));
                if (getFileHandler().exists()) {
                    getFileHandler().delete(); // the result gets ignored because there's no need for it.
                }
                else {
                    errorMessage = "Error. The journal entry has already been deleted";
                    return errorMessage;
                }
            }
            catch (Exception e) {
                errorMessage = "Error. The journal entry could not be deleted";
                return errorMessage;
            }
        }
        errorMessage = "Journal entry has been successfully deleted"; // the error message prompt gets reused to tell the user
                                                                        // if the journal entry was successfully deleted or not.
        return errorMessage;
    }
}