<div style="text-align: justify;">
<p class="paragrafo">
Na literatura são encontrados alguns trabalhos que tem como objetivo auxiliar a utilização de técnicas de inteligência computacional, entretanto nenhum deles dispõe de uma interface gráfica para simplificar o trabalho dos usuários. Dentre esses trabalhos destacam-se a ferramenta CIlib (Computational Intelligence Library) (PAMPARA; ENGELBRECHT; CLOETE, 2008), o framework JMetal (DURILLO; NEBRO; ALBA, 2010) e o trabalho dos pesquisadores R. A. Ellen e D. A. Campbell (ELLEN; CAMPBELL, 2005) com a criação de um framework para execução de algoritmos de inteligência computacional em sistemas embarcados e distribuídos.
</p> 
<p class="paragrafo">
A CIlib é um projeto open source (GPL License), hospedado no SourceForge.net (http://cilib.sourceforge.net). Seu objetivo é prover a construção de algoritmos de IC com o mínimo de esforço, utilizando componentes reutilizáveis. Essa ferramenta foi desenvolvida pelo grupo de pesquisa CIRG (Computational Intelligence Research Group) da Universidade de Pretória, África do Sul. Sua abordagem é bem parecida com a proposta da ferramenta Athena, entretanto a CIlib não dispõe de uma interface gráfica para a configuração dos sistemas computacionais. Esse processo ainda é feito utilizando arquivos no formato XML, o que dificulta sua utilização por pesquisadores não ligados diretamente a área da Computação.
</p>
<p class="paragrafo">
Outro ponto negativo para a ferramenta CIlib, diz respeito a necessidade de baixar a ferramenta para executar as simulações na máquina do próprio cliente. Na Athena as simulações são realizadas em ambiente de nuvem e não consomem recursos da máquina dos usuários.
</p>
<p class="paragrafo">
Outro trabalho que visa auxiliar a utilização de técnicas de inteligência computacional é o framework de metaheurísticas aplicadas a resolução de problemas de otimização multiobjetivo JMetal. Ele foi escrito na linguagem de programação Java, e possui um conjunto extenso de metaheurísticas já implementadas, bem como uma interface bem definida para a utilização das mesmas; além de fornecer diversas estruturas de dados para a implementação da representação do problema que se quer resolver.
</p>
<p class="paragrafo">
Apesar do JMetal trabalhar com um nicho bem especifico de algoritmos e não se tratar de uma ferramenta propriamente dita, sua análise foi importante para esse trabalho porque suas estruturas internas assemelham-se às estruturas da Athena. Basicamente o JMetal possui uma classe Algoritmo (Algorithm) que soluciona um Problema (Problem) usando um conjunto de Soluções (SolutionSet) e um conjunto de Operadores (Operator). Alguns desses conceitos foram incorporados e apoiaram a construção do módulo NSGAII da Athena.
</p>
<p class="paragrafo">
Por fim o trabalho dos pesquisadores R. A. Ellen e D. A. Campbell, intitulado A Framework For Executing Computational Intelligence Over Distributed Embedded Nodes descreve um framework para execução de algoritmos de IC em sistemas embarcados e distribuídos. A arquitetura dos componentes foi definida com base no padrão de objetos baseados em portas, esse modelo permite um baixo acoplamento entre os módulos e torna o sistema mais flexível. Essa ideia também foi utilizada na Athena, entretanto ao invés de entradas/saídas de controles a Athena dispõe de módulos com configurações de execução.
</p>
</div>
