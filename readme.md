# Import und Start
## Import
Zum Import �ber IntelliJ muss die Datei "build.gradle" imporitert werden. Alles weitere dann �ber "Autoimport"

## Ausf�hren
Um das Projekt zu starten muss explizit die Klasse "DesktopLauncher" ausgef�hrt werden (desktop - src - com.bmeproject.game.desktop - DesktopLauncher)

# Terminplan
### Ende November: 
Richtungsfindung (f�r Arbeitsbereiche);
Erste Code-Basis;
### Ende Januar: 
Prototyp: erste Funktionalit�ten laufen (�begehbare Baustelle�);

>##### in Arbeit:
## Code Conventions
### Dokumentenstruktur:
1. class / interface JavaDoc documentation
2. class / interface declaration
3. static variables / constants (public - protected - private)
4. instance variables (public - protected - private)
5. constructors
6. methods
### Declarations:
- one declaration per line
- local variables should be initialised when declared
- declare local variables at the beginning of the block where they are used
- if statements should always have an else statement
- if statements must have brackets {} 
### Naming Conventions:
- packages: always use them, never use the default package, they should be named like: com.sun.eng
- classes / interfaces should be nouns, starting with an uppercase letter (Pascal Case)
- interfaces always start with an capital I (ICard, IBoard etc.)
- methods should be verbs, starting with lowercase letter (Camel Case)
- methods with similar functionality should be named similar (getX, getY, checkForX, checkForY, etc.)
- constants should always be all uppercase and words should be separated with "_"
>##### Quelle: Script Software-Quality, Oracle Codeconventions 