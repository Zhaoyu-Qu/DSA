/**
 * The program requests two n × n matrics from the user and computes the product using the Strassen Algorithm
 * 
 * How does it work?
 * Suppose we have two 4 × 4 matrices below:
 * 
 * |a1 a2 a3 a4|     |e1 e2 e3 e4|
 * |b1 b2 b3 b4|     |f1 f2 f3 f4|
 * |c1 c2 c3 c4|     |g1 g2 g3 g4|
 * |d1 d2 d3 d4|  ×  |h1 h2 h3 h4|
 * 
 * The product of them will be
 * 
 * |a1e1+a2f1+a3g1+a4h1 a1e2+a2f2+a3g3=2+a4g2 ... ...|
 * |...                 ...                   ... ...|
 * |...                 ...                   ... ...|
 * |...                 ...                   ... ...|
 * 
 * The runtime of the algorithm above is O(n^3)
 * 
 * Now, the two 4 × 4 matrices can be split into four 2 × 2 matrices, each quadrant becoming a sub-matrix
 * 
 * |A B|     |E F|
 * |C D|  ×  |G H|
 * 
 * The product of them will be 
 * 
 * |AE+BG AF+BH|
 * |CE+DG CF+DH|
 * 
 * Because each of the letters represents a sub-matrix, from here we can solve the program recursively.
 * Unfortunately, this algorithm entails 8 recursive calls. The runtime is still O(n^log2(8)) or O(n^3).
 * 
 * Volker Strassen came up with this genius idea that reduces the number of recursive calls to seven.
 * As a result, the time complexity drops from O(n^3) to O(n^log2(7)) or O(n^2.8)
 * 
 * Here is how Strassen's algorithm works
 * The Strassen algorithm now claims that
 * 
 * |A B|     |E F|     |AE+BG AF+BH|     |M1+M4-M5+M7  M3+M5      |
 * |C D|  ×  |G H|  =  |CE+DG CF+DH|  =  |M2+M4        M1-M2+M3+M6|
 * 
 * 
 * where
 * M1 = (A+D)×(E+H)
 * M2 = (C+D)×E
 * M3 = A×(F-H)
 * M4 = D×(G-E)
 * M5 = (A+B)×H
 * M6 = (C-A)×(E+F)
 * M7 = (B-D)×(G+H)
 * 
 * And it's true. If we expand out this crazy expression M1+M4-M5+M7, we will end up with exactly AE+BG.
 * As such, the number of recursive calls has now reduced from 8 to 7. 
 * The time complexity drops from O(n^3) to O(n^log2(7)) or O(n^2.8)
 * 
 */