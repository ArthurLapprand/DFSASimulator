set xlabel "Número de etiquetas"
set ylabel "Número de slots"
set key vertical top left
set grid
set pointsize 2
plot "LowerBound_64_1100_10_2000.txt" u 1:2 t 'LowerBound' w linespoints, \
