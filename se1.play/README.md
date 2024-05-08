<!-- check-out single branch from repo:
        git clone -b a123 --single-branch https://github.com/sgra64/se1.play.git
 -->

# Aufgaben A1, A2 und A3


1. [Aufgabe A1](#a1-setup-java-ide-terminal) - Setup Java (IDE, Terminal) -- (4 Pts)

1. [Aufgabe A2](#a2-projekt-anlegen-und-helloworld-java) - Projekt anlegen und *HelloWorld.java* (2 Pts)

1. [Aufgabe A3](#a3-numbers) - *Numbers* (4 Pts)


&nbsp;

## A1: Setup Java (IDE, Terminal)

Sie sollten JDK Java-21 (LTS - Long-term support Version) auf Ihrem System haben
(von Oracle *HotSpot* oder *OpenJDK*). Achten Sie darauf, dass Sie ein *JDK*
(Java Develoment Kit) installiert haben und nicht nur *JRE* (Java Runtime Environment).

1. Wählen Sie eine IDE (Integrated Development Environment). Empfohlen ist *VS Code*,
    Sie können aber auch *eclipse* weiter verwenden oder *IntelliJ*.

1. **Für Windows**, installieren Sie bitte `cygwin` (Unix-Emulator), folgen Sie den Schritten in
    [setup_cygwin](https://github.com/sgra64/markup/blob/main/setup_cygwin/README.md)

1. **Alle** (Mac, Linux, Windows), falls im nächsten Schritt Fehler auftreten,
    schauen Sie bitte, dass Sie
    [.bashrc und .zshrc Files im $HOME Directory](https://github.com/sgra64/markup/tree/main/setup_rcfiles)
    richtig gesetzt haben.

1. Öffnen Sie ein Terminal und prüfen Sie, ob alle Werkzeuge korrekt installiert sind
    und aufgerufen werden können.

    ```
    java --version
    ```
    Antwort: Java-JDK, Version 21 (Oracle)
    ```
    java 21 2023-09-19 LTS
    Java(TM) SE Runtime Environment (build 21+35-LTS-2513)
    Java HotSpot(TM) 64-Bit Server VM (build 21+35-LTS-2513, mixed mode, sharing)
    ```

    ```
    javac --version
    javac 21
    ```

    ```
    javadoc --version
    javadoc 21
    ```

    ```
    jar --version
    jar 21
    ```

    ```
    git --version
    git version 2.38.0.windows.1
    ```


&nbsp;

## A2: Projekt anlegen und *HelloWorld-java*

Legen Sie in Ihrem $HOME-Verzeichnis ein Verzeichnis `workspaces` an und
in diesem ein Projekt `se1.play`.

1. Erstellen Sie in diesem Projekt eine Java-Klasse `HelloWorld.java`, die
    [*"Hello World!"*](https://en.wikipedia.org/wiki/%22Hello,_World!%22_program)
    ausgibt.

1. Führen Sie das Programm in der IDE aus.

1. Führen Sie das Programm `HelloWorld.java` bzw. dessen übersetzte Form
    `HelloWorld.class` im Terminal aus.
    Setzen Sie dazu im Terminal die Variable *CLASSPATH*.

    ```sh
    java HelloWorld
    ```
    Ausgabe:
    ```
    Hello World!
    ```

1. Ergänzen Sie das Programm, so dass Sie Ihren Namen mit anfügen können:

    ```sh
    java HelloWorld Brian
    Hello World, Brian!
    ```
    (*Brian Kernighan* ist der Erfinder von *"HelloWorld"* - was haben er,
     *Ken Thompson* und *Dennis Richie* noch erfunden?)


&nbsp;

## A3: Numbers

`1.)` Schreiben Sie ein weiteres Java-Programm *Numbers*, das die als Argumente
übergebenen Zahlen addiert.

Beispiel der Ausführung des Programms `Numbers`:

```
java Numbers 3 4 8
```

Ausgabe:

```
Hello, Numbers (sum args)
--> sum is: 15
```

Für die Zahenfolge: `3 9 -4 2 6` ergibt sich `--> sum is: 16`.


&nbsp;

`2.)` Erweitern Sie das Programm, so dass auch geschriebene Zahlen (*"null"* bis *"zwanzig"*)
akzeptiert werden.

```
java Numbers vier 7 Achtzehn
--> sum is: 29
```

Fehler sollen gemeldet werden:

```
java Numbers vier 7 dreimal Achtzehn
```

```
Hello, Numbers (sum args)
ich verstehe "dreimal" nicht (ignoriert)
--> sum is: 29
```

Probieren Sie eine größere Menge an Zahlen:

```
java Numbers 799 237 936 vier 1342 neun 1635 4676 1613 3851 \
     1445 4506 3346 acht 2141 2064 1491  908   78 3325 \
     1756 3691   23 1995 1800  -15 2784 4305   36 2532 \
     4292 4802 2522 4183 3261 2610  803 2656  498 1668 \
     2038 2194  440 -463 4047 4235 3931  756  521 4042 \
     3302  485 1002 -408 4691 3387 achtzehn -2241 4382 \
     1220 3656 -500 zwanzig null null null zwoelf neun
```

Ergebnis:

```
Hello, Numbers (sum args)
--> sum is: 121392
```


&nbsp;

`3.)` Kann man das Programm ohne Schleifen (`for`, `while`, `do-while`) schreiben?

Versuchen Sie es.


&nbsp;

`4.)` Ändern Sie das Programm, so dass die Ausführung über Parameter (*key*=*value*)
erfolgen kann. Beachten Sie die Quotierung und Kommas bei den Zahlen, z.B. bei *"4, 7, 18"*.

```
java Numbers sum=29 n="4, 7, 18"
```

```
Hello, Numbers (sum args)
--> Ihre Lösung war korrekt
--> sum is: 29
```

Oder bei falscher Antwort:

```
java Numbers sum=28 n="4, 7, 18"
```

```
Hello, Numbers (sum args)
--> Ihre Lösung (sum=28) war leider falsch
--> sum is: 29
```

Zeigen Sie das Verhalten an anderen Beispielen.

```
java Numbers sum= n="4, sieben, 18, null, vier, dreifach, zwoelf"
```

```
Hello, Numbers (sum args)
--> Ihre Lösung (sum=28) war leider falsch
--> sum is: 29
```
