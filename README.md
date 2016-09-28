# What this is

TODO: Make this README better. PLS, Neveen. Do ASAP.

This is a work-in-progress pavement design tool. The decision-making
process includes performance characteristics (with the help of
AASHTOWare ME Design) in addition to environmental characteristics
and sustainability factors.

# Installing and running

## Dependencies

- [sqlite-jdbc-3.8.11.2.jar](https://bitbucket.org/xerial/sqlite-jdbc/downloads/sqlite-jdbc-3.8.11.2.jar)
- Get these files from Neveen:`rt.db` and `test.db`

## Running the program

For now, it's best to open this as a project in IntelliJ IDEA. Then
runn `cm.App.main()`.

# Background

## Problem

There is consensus that a reliable method to quantify pavement
sustainability is needed.  State of the art promotes the use of life
cycle assessment for quantification of pavement sustainability.
While Life Cycle Assessment (LCA) is a promising tool for evaluating
materials’ sustainability from cradle to grave, it is time-consuming
and the data used can be outdated and/or inaccurate. Various Life
Cycle Assessment (LCA) models/tools exist including eco-invent;
Gabi; Semipro, and sustainable minds.  The main deficiency of these
tools is that their data might be biased or incomplete. Furthermore,
discrepancies emerge when each model has its own data sources. Such
discrepancies could be related to the data sources, outdated data,
data accuracy, and geographical representation and system
boundaries. This renders the use of LCA challenging, time-consuming
and highly unreliable. Therefore, the problem boundary is usually
limited decreasing the level of reliability associated with the LCA
results. 

Given the aforementioned limitations and to overcome both the
discrepancy and the time issues, an innovative reliable decision
making tool is needed if sustainability is to be used by DOT
agencies as a design component. 

## Objective

The objective of the proposed study is to conceive and develop a
decision-making tool for evaluating sustainability of pavement
designs based on a cradle to gate analysis. This tool will utilize
Environmental Product Declarations (EPD) to enhance the reliability
of the assessment data and will be integrated within state of the
art pavement design methods such as Pavement ME.  The proposed tool
will be easy to use by pavement designers and decision makers; it
will also allow for evaluating alternative designs. 

## Methodology

An extensive literature review will be performed to present current
state practices and studies that evaluate pavement sustainability
quantification methods from published materials and ongoing
projects. The collected and reviewed literature will provide the
research team with valuable information as related to the following
topics:

- State practices in using pavement sustainability quantification
  methods;
- Knowledge gaps and unresolved issues with respect to pavement
  sustainability quantification

A database that compiles Environmental Product Declaration (EPD) for
concrete and available asphalt pavement products will be designed
and compiled. Since EPDs are dependent on their associated Product
Category Rule (PCRs), which reports the content of EPD and their
limitations.  the PCRs will be linked to the EPD to inform the user
of the quality of information extracted.

The developed database will be integrated as part of a pavement
sustainability design tool that measures environmental and economic
sustainability based on a cradle to gate analysis.  The tool will be
linked with the Pavement MEPDG design software. It will use the
MEPDG software design output as search criteria in the data base to
select the most sustainable mix design based on environmental and
economic performance.

Demonstration of the developed sustainable pavement design tool will
be conducted for different design cases including a rigid pavement
and a flexible pavement.  Projects will be selected in coordination
with LADOTD. The optimum design will be selected based on
performance, environmental, and economic factors.

## Implementation Potential

The developed pavement design sustainability decision tool will be
user friendly and be integrated in LADOTD pavement design processes
by the end of this project.  It will allow the designers to compare
alterative pavement designs that satisfy performance criteria from
an environmental and economic perspective to enhance the
sustainability of pavement design in LA.  The developed tool will
impact highway contractors, DOTs, transportation and civil
engineers.  It will help put Louisiana on the Map as leader in
pavement sustainability.
