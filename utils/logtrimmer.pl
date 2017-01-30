#!/usr/bin/perl

use strict;
use warnings;

my $filename = $ARGV[0]; # input is just algo name

open(my $fh2, '<', $filename)
    or die "Could not open out file '$filename' $!";

my $odd =1;

while (my $row = <$fh2>) {
    if ($odd % 2 == 0){
	chomp $row;
	print $row."\n";
    }
    $odd += 1;
}
