# IBAN Validator

## Programos paleidimas:

 - Terminale atidarykite aplanką 'test-jar', kuriame rasite sukompiliuotą 'SEB-1.0.jar' failą.
 - Terminalo sąsajoje įveskite: 'java -jar SEB-1.0.jar --help' ir pasirodys informacija apie visas esamas opcijas.
 - Norint testuoti pavienius IBAN numerius (max. 10 IBAN), terminale įveskite: 'java -jar SEB-1.0.jar -i=iban1,iban2,iban3...'
 - Norint testuoti IBAN numerius iš tekstinio failo, terminale įveskite: java -jar SEB-1.0.jar -f=kelias_iki_txt_failo, po    paleidimo, 'test-jar' aplanke turėtų atsirasti rezultatų failas.

# !! DĖMESIO !!
    
 Sukompiliuotas .jar failas, taip pat automatiškai paleidžią Web serverį ant 8080 porto, dėl to prieš paleidžiant programą iš naujo, būtina ją sustabdyti, tai galite padaryti paspaudę Ctrl+C.


## REST API testavimas:

 - API turi vieną end-point http://localhost:8080/api/validate, jam reikia perduoti tvarkingą sąrašą su JSON objektais, kuriuose kiekvienas turėtų po "iban" laukelį. 
 - Gausite atsakymą JSON formatu, kuriame atsiras papildomas laukelis isValid su apskaičiuotu rezultatu.
 - Lengviausia testuoti pasitelkiant curl biblioteką arba Postman.


## cURL pavyzdys:

```
curl -X POST \
  http://localhost:8080/api/validate \
  -H 'Content-Type: application/json' \
  -d '[
    {
        "iban": "LT517044077788877777"
    },
    {
        "iban": "LT647044001231465456"
    },
    {
        "iban": "LT227044077788877777"
    },
    {
        "iban": "CC05124544545455211798"
    },
    {
        "iban": "LT647044001231465456"
    }
]'
```

Atsakymas:
```
[
  {
    "iban": "LT517044077788877777",
    "isValid": true
  },
  {
    "iban": "LT647044001231465456",
    "isValid": true
  },
  {
    "iban": "LT227044077788877777",
    "isValid": false
  },
  {
    "iban": "CC05124544545455211798",
    "isValid": false
  },
  {
    "iban": "LT647044001231465456",
    "isValid": true
  }
]
```
