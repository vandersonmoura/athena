<div style="text-align: justify;">
<p class="paragrafo">
Na literatura s�o encontrados alguns trabalhos que tem como objetivo auxiliar a utiliza��o de t�cnicas de intelig�ncia computacional, entretanto nenhum deles disp�e de uma interface gr�fica para simplificar o trabalho dos usu�rios. Dentre esses trabalhos destacam-se a ferramenta CIlib (Computational Intelligence Library) (PAMPARA; ENGELBRECHT; CLOETE, 2008), o framework JMetal (DURILLO; NEBRO; ALBA, 2010) e o trabalho dos pesquisadores R. A. Ellen e D. A. Campbell (ELLEN; CAMPBELL, 2005) com a cria��o de um framework para execu��o de algoritmos de intelig�ncia computacional em sistemas embarcados e distribu�dos.
</p> 
<p class="paragrafo">
A CIlib � um projeto open source (GPL License), hospedado no SourceForge.net (http://cilib.sourceforge.net). Seu objetivo � prover a constru��o de algoritmos de IC com o m�nimo de esfor�o, utilizando componentes reutiliz�veis. Essa ferramenta foi desenvolvida pelo grupo de pesquisa CIRG (Computational Intelligence Research Group) da Universidade de Pret�ria, �frica do Sul. Sua abordagem � bem parecida com a proposta da ferramenta Athena, entretanto a CIlib n�o disp�e de uma interface gr�fica para a configura��o dos sistemas computacionais. Esse processo ainda � feito utilizando arquivos no formato XML, o que dificulta sua utiliza��o por pesquisadores n�o ligados diretamente a �rea da Computa��o.
</p>
<p class="paragrafo">
Outro ponto negativo para a ferramenta CIlib, diz respeito a necessidade de baixar a ferramenta para executar as simula��es na m�quina do pr�prio cliente. Na Athena as simula��es s�o realizadas em ambiente de nuvem e n�o consomem recursos da m�quina dos usu�rios.
</p>
<p class="paragrafo">
Outro trabalho que visa auxiliar a utiliza��o de t�cnicas de intelig�ncia computacional � o framework de metaheur�sticas aplicadas a resolu��o de problemas de otimiza��o multiobjetivo JMetal. Ele foi escrito na linguagem de programa��o Java, e possui um conjunto extenso de metaheur�sticas j� implementadas, bem como uma interface bem definida para a utiliza��o das mesmas; al�m de fornecer diversas estruturas de dados para a implementa��o da representa��o do problema que se quer resolver.
</p>
<p class="paragrafo">
Apesar do JMetal trabalhar com um nicho bem especifico de algoritmos e n�o se tratar de uma ferramenta propriamente dita, sua an�lise foi importante para esse trabalho porque suas estruturas internas assemelham-se �s estruturas da Athena. Basicamente o JMetal possui uma classe Algoritmo (Algorithm) que soluciona um Problema (Problem) usando um conjunto de Solu��es (SolutionSet) e um conjunto de Operadores (Operator). Alguns desses conceitos foram incorporados e apoiaram a constru��o do m�dulo NSGAII da Athena.
</p>
<p class="paragrafo">
Por fim o trabalho dos pesquisadores R. A. Ellen e D. A. Campbell, intitulado A Framework For Executing Computational Intelligence Over Distributed Embedded Nodes descreve um framework para execu��o de algoritmos de IC em sistemas embarcados e distribu�dos. A arquitetura dos componentes foi definida com base no padr�o de objetos baseados em portas, esse modelo permite um baixo acoplamento entre os m�dulos e torna o sistema mais flex�vel. Essa ideia tamb�m foi utilizada na Athena, entretanto ao inv�s de entradas/sa�das de controles a Athena disp�e de m�dulos com configura��es de execu��o.
</p>
</div>
