# RispondiDomande

Small Java-FX project for self-generating multiple choice tests out of a question pool.

## How to use

The program needs a set number of questions from which to choose a random subset each time it's run. Given N questions, each question must be put in its own file, must be named from 0.txt to n-1.txt and follow the following format:

```text
QUESTIONNUMBER NUMBEROFANSWERS RIGHTANSWER
This line will contain the question.
This line will contain answer A.
This line will contain answer B.
This line will contain answer C.
This line will contain answer D (optional) / Blank line.
This line will contain answer E (optional) / Blank line.
This line will contain answer F (optional) / Blank line.
```

QUESTIONNUMBER must be the same number as the file (QUESTIONNUMBER.txt). NUMBEROFANSWERS can either be an integer from 3 to 6. RIGHTANSWER must be a number between 1 (representing A) and 6 (F) depending on the number of answers provided.

All files must be put in a the folder /src/domande.

Field *domandetotali* in Domanda.java must be changed accordingly to the number of files.
To change the number of picked questions each run, change the field *numerodomande* in DomandaBox.java.

## Attachments

The program supports attachments in questions. If a questions needs a separate text file to be shown, you can do it by adding under /src/domande an additional file called QUESTIONNUMBER.code. When the corresponding question is called, the program will automatically pull the additional text for you and position it in the TextArea after the questions.

## Authors

* **Matteo Franzil** - *Initial work* - [mfranzil](https://github.com/mfranzil)

## License

This project is open source. Please, contact me for suggestions and reviews.
