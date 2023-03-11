/**
 * The program requests two n × n matrics from the user and compute the product using the Strassen Algorithm
 * 
 * How does it work?
 * Suppose we have two 4 × 4 matrices below:
 * 
 * |a1 a2 a3 a4|    |e1 e2 e3 e4|
 * |b1 b2 b3 b4|    |f1 f2 f3 f4|
 * |c1 c2 c3 c4|    |g1 g2 g3 g4|
 * |d1 d2 d3 d4|    |h1 h2 h3 h4|
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
 * Now, the two 4 × 4 matrices can be divided into four 2 × 2 matrices, each quadrant becoming a sub-matrix
 * 
 * |A B|    |E F|
 * |C D|    |G H|
 * 
 * The product of them will be 
 * 
 * |AE+BG AF+AH|
 * |CE+CG CF+CH|
 * 
 * Because each of the letters represent a sub-matrix, from here we can solve the program recursively.
 * Unfortunately, this algorithm entails 8 recursive calls. The runtime is still O(n^log2(8)) or O(n^3).
 * 
 * Volker Strassen came up with this genius idea that reduces the number of recursive calls to seven.
 * As a result, the time complexity drops from O(n^3) to O(n^log2(7)) or O(n^2.8)
 * 
 * Here is how Strassen's algorithm works
 * 
 * 
 */