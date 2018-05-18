# RispondiDomande
Small Java-FX project for self-generating multiple choice tests out of a question pool.

# How to use
The program needs a set number of questions from which to choose a random subset each time it's run. Given N questions, each question must be put in its own file, must be named from 0.txt to n-1.txt and follow the following format:
```
QUESTIONNUMBER NUMBEROFANSWERS RIGHTANSWER
This line will contain the question.
This line will contain answer A.
This line will contain answer B.
This line will contain answer C.
This line will contain answer D (optional).
Blank line.
```
QUESTIONNUMBER must be the same number as the file (QUESTIONNUMBER.txt). NUMBEROFANSWERS can either be 3 or 4. RIGHTANSWER must be a number between 1 (representing A) and 3 (C) or 4 (D) depending on the number of answers provided.

All files must be put in a subfolder called /domande under /src.

Field *domandetotali* in Domanda.java must be changed accordingly to the number of files.
To change the number of picked questions each run, change the field *numerodomande* in DomandaBox.java.

## Authors

* **Matteo Franzil** - *Initial work* - [mfranzil](https://github.com/mfranzil)

## License

This project is open source. Please, contact me for suggestions and reviews.

