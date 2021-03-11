# [Sr Java](https://youtu.be/160QH3Gi56Y)

## We have a list of Strings containing Twitter messages. Task:

Implement a method, which returns the list of unique hashtags used in these messages.  
Result hashtags should be sorted by usage frequency

```Java
List<String> twits = new ArrayList<>();
twits.add("#Java and #Scala are the languages of cognitive and AI development. IBM sees cognitive development is the future.");
twits.add("Some more info on the IBM investment in #Scala and Lightbendhttp8/ibm-lightbend-join-forces-enterpri");
twits.add("IBM and @lightbend are building an integrated platform for #Java and #Scala #cognitive app development");
public List<String> getHashtags(List<String> twits) { }
```

Result: ["Scala", "Java", "cognitive"]

## SQL

```sql
create table Users ( id bigint primary key, full_name varchar, age int );
create table Books ( id bigint primary key, title varchar, pages bigint );
create table Records ( id bigint primary key, book_id bigint, user_id bigint );
```

select top 3 most popular books among readers younger than 10 years
