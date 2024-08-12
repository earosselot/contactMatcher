# Contact Comparator App

This is a java application that will identify possible duplicate amongst a list of 
contacts provided in a .csv file and output an output.csv file with the contact matches
found.

## Run the app

```bash
mvn package
java -jar target/contactMatcher-1.0.jar contacts.csv
java -jar target/contactMatcher-1.0.jar "Code Assessment - Find Duplicates Input - SampleCodecsv.csv"
```

## Run tests only

```bash
mvn test
```

## How does it compare ? 

To compare two contacts the application generates a `ContactComparator` with a list of 
`ComparatorScores`. The `ComparatorScores` will hold the information regarding the scores
each field will provide in the cases of full match, partial match or initial match.
The comparison of the contacts is based on a likelihood value which is the sum of the scores
for each field based on the `ComparatorScores` provided. This is configurable,
but the application has default scores to avoid excessive configuration.

The default scores are:

- Field, Full Match, Partial Match, Partial Match Threshold, Initial Activated, Initial Match
- first name, 30, 25, 1, true, 15
- last name, 30, 25, 1, true, 15
- email, 30, 25, 2, false, 0
- zip code, 5, 2, 2, false, 0
- address, 5, 2, 2, false, 0

To explain this lets review an example
`Contact1 = "Rob", "B", "robert@gmail.com", "12354", "Fake Street 123"`
`Contact1 = "Rob", "Brown", "robert12@gmail.com", "1", "Fake Street 123"`

- The name is a Full Match, that is `30` points. 
- The last name is an Initial Match, that is `15` points.
- The email is a Partial Match, `15` points.
The explanation on why this is a partial match will be below.
- The zip code, looks like an Initial Match, but zip codes don't allow for that kind of match
(`InitialActivated = false`). Thus, `0` points.
- The address is a Full Match, that is `5` points more.
- Summing the above we can say that the likelihood score among this 2 contacts is `65`.

At this point there is a `ScoreMapper` that will transform scores into accuracy.
Again, this is also configurable, but the default values are:
- score >= 75 -> High accuracy match
- score >= 50 -> Medium accuracy match
- score >= 35 -> Low accuracy match
- score < 35 -> Not a Match

## Avoid duplication

When a contact is matched against another, it will not be compared again.
This is to avoid duplication and to complaint with the example.
E.g.: If contact 1, 2 and 3 have above Low matching score, then the result will contain 
match(1, 2) and match(1, 3), but no match(2, 3).

## Similar Fields and Levenshtein Distance

To determine if two fields of contacts are similar, for example due to typos. The app 
utilized the Levenshtein distance. This is the distance between string1 and string2 is counted on 
the number of operations to reach string2 from string1. The possible operations are: delete a character,
add a character, and change a character.

This distance is calculated on O(n+m) time complexity where n and m are the lengths of string1 
and string2 respectively.

## Types of Matches

### Full Match

This is the simpler of all matches, it verifies full equality between 2 strings despite case.
Examples of full matches:
- "Jhon" and "jhon"
- "FRED" and "FRED"

### Partial Match

A partial match is a match where the Levenshtein distance between two fields is less than a threshold.
Examples of Partial Matches with Threshold 2:
- "Jhon" and "jhonny"
- "Jhon" and "Jh"
- "jhin" and "JHAN"

It is good to note that the threshold plays an important role to determine similarity among strings,
a value too low will be less flexible (value 0 is the same as full match), and a value to large will
lead to a great amount of false positives.

### Initial Match

This match can only occur when one of the strings is a single letter. And it will compare for equality
the first letter of the long string with the only letter of the short string.
Examples of Initial Match are:
- "Jhon" and "J"
- "Jhon" and "j"
- "S" and "sam"

This comparison will be performed on the fields that has initial match activated only. This is because
from my point of view there is no point on match an email, or a zip code for an initial. Again, this is
configurable and can be changed.

### Possible Improvements

- Based on experience the Thresholds for similarity and for matches can be tuned. After watching the examples,
I think I have put to low scores on name initials and too much on name similarities.
- Email local-part and domain can be analyzed separately, given the local-part is much more important to detect 
duplicated contacts than the domain.
- Instead of avoiding duplication from start, it could be changed to consider the most similar contact, instead of 
the first. E.g.: contact1 is similar to contact2 with low accuracy. This will lead to contact3 not being able to
be compared with contact2 even when they might be similar with high or medium accuracy.
