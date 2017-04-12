#!/bin/bash          

##########################################
# Script to start the antlr Parse Tree Inspector
#
# NOTE: run using "source CSTgui.sh"
##########################################

##########################################
# Setup variables:
##########################################

# Parser package name
PARSERNAME=org.frallan.openmpparser.language.C
# Main grammar rule
GRAMMARRULE=compilationUnit
# Path to file to be parsed (relative to target/classes)
TOKENPATH=../../openMPfiles/sinTable.c

##########################################
# Script body:
##########################################

echo Starting CST gui analyzer...

export CLASSPATH=".":$HOME"/.m2/repository/org/antlr/antlr4/4.5.3/antlr4-4.5.3.jar"
alias grun='java org.antlr.v4.gui.TestRig'

cd target/classes
grun $PARSERNAME $GRAMMARRULE -token $TOKENPATH -gui
cd ../../

echo CST gui analyzer ended.
