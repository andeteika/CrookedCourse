# CrookedCourse

## Introduction
SOC Games Ltd has recently been awarded the contract to build a prototype game based on the forthcoming 2024 Rabbit Show
Jumping World Championships, a sport which combines the intellectual and physical challenge of equestrian show jumping 
with the thrill and excitement of rabbits. 

It is anticipated that, if the proof-of-concept is successful, it could lead to a game series similar to the FIFA series
for football. For obvious reasons, you should use the regulations set down by the Swedish National Federation of Rabbit 
Jumping (Kaninhoppning) at https://drive.google.com/file/d/13UWpWLPTNjPG7u4kDEBJUb1OVqh0dmdc/view.

## Task
You have to write and test a set of Java classes that can be used as the basis of a software model of the competition. 
To keep things simple, you should create a simulation of the “Crooked Course” event.

The program has to simulate the major aspects of the event such as the properties and behaviour of the contestants, the
properties of the owners, the World Cup league, and the competition itself. Basic Rules for Rabbit Jumping (Based on the
Swedish Federation Competition Rules summary: http://skhrf.se/english/).

The sport has four different events: Straight course, Crooked course, High Jump and Long Jump. You should create a 
software model of the Crooked Course event. You may assume that there will be a total of ten rabbits in the competition,
each from a different country.

A crooked course is not dissimilar to show jumping with horses. The jumps are on a course that twists and turns, with 
different distances between jumps, whereas, on a straight course, the jumps are placed along a straight line, with 
exactly the same distance between each jump.
The aim is to jump the course, without incurring any faults. For every jump that is knocked down, one fault is given. 
There is also a time limit for course completion which is set at 250 seconds.

Note that if a rabbit is lifted over a jump that is not already knocked down, it will also receive a fault. In the case 
of a rabbit exceeding the time limit or jumping the course in the wrong order, it will be given a score of zero for that
round. It is essential that the rabbit jumps of its own free will and is not forced. The rabbit must also be in front of
the owner.

To participate in a competition, the handler must have turned seven years old and the rabbit must be at least four 
months old. All breeds and crossbreeds are allowed to participate, the only basic requirement is that the rabbit must be
healthy.

There are four official levels of difficulty in the Crooked course event: Easy, Medium, Difficult and Elite. The levels
decide the height and length of jumps, the number of jumps and the technical difficulty of the jumps.
The height and length of the different levels are as follows:

| Difficulty Level | Maximum Height | Maximum Length | Minimum Number of Jumps | Difficulty Factor |
|------------------|----------------|----------------|-------------------------|-------------------|
| Easy             | 30 cm          | 45 cm          | 8                       | 1                 |
| Medium           | 38 cm          | 65 cm          | 10                      | 2                 |
| Difficult        | 45 cm          | 80 cm          | 10                      | 3                 |
| Elite            | 50 cm          | 80 cm          | 12                      | 5                 |  

The score of the rabbit in each round is calculated by finding the number of faultless jumps, multiplying this number
by the difficulty factor, and then dividing the result by a time factor. The time factor is calculated by dividing the 
time for the round (in seconds) by the time limit.
The overall score for a contestant is the sum of the scores from each round.

- Thus, Fluffy, who completed the Medium level in 222 seconds with 5 faults would have a score for that round of 
((10 – 5) x 2 )/(222/250) = 11.26
- Meanwhile, Floppy, who completed the Elite level in 240 seconds with 2 faults would have a score for that round of 
((12 – 2) x 5 )/(240/250) = 52.08
- However, Fiver, who completed the Difficult level in 260 seconds without any faults would have a score of zero for
that round as the time limit was exceeded.
