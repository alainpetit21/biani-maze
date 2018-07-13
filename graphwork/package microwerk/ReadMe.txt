I am creating a port of a old popular Sega master system game called "snail Maze" and I need assistance with the level design on the maze.

What is needed to be done is to take the pictures and convert the maze into a code-compatible array. There are 12 level to do, 30x16 squares.

Each wall are coded using a binary representation
right wall is 0x1
bottom wall is 0x2
left wall is 0x4
top wall is 0x8

and every combinaison are addition in binary form. For instance, Top-Left corner is 0xC (0x8 + 0X4).

You will create individual text file for each levels, named result_##.txt.

In the attachment, you will see an expected result test file result_00.txt As well as some other Descriptive images
