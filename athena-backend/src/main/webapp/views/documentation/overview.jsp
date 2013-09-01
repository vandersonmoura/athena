<div style="text-align: justify;">
<p class="paragrafo">O objetivo da Athena � possibilitar a cria��o de sistemas inteligentes h�bridos, de maneira simples e transparente. 
Dessa forma a ferramenta pode ser utilizada por pesquisadores, desenvolvedores e usu�rios que detenham o m�nimo de 
conhecimento em t�cnicas de intelig�ncia computacional.</p>
	
<p class="paragrafo">Para que esse objetivo se concretize, o desenvolvimento da ferramenta foi guiado pelas seguintes metas:</p>

<ul style="margin-right: 10px;">
<li><strong>Simplicidade:</strong> Um dos problemas associado � utiliza��o de t�cnicas de intelig�ncia computacional est� diretamente ligado � 
complexidade de determinados algoritmos. Isso acaba dificultando sua utiliza��o por pesquisadores de �reas n�o ligadas � computa��o.</li>
<li><strong>Extensibilidade:</strong> A Athena deve prover uma arquitetura robusta para suportar a complexidade dos algoritmos de IC e ao mesmo tempo 
deve ser flex�vel para facilitar a inclus�o de novos algoritmos e novos componentes.</li>
<li><strong>Software como servi�o (SaaS):</strong> Em geral a maioria das ferramentas que visam apoiar t�cnicas de IC est�o dispon�veis para download 
e devem ser integradas ao projeto em desenvolvimento. Dessa forma, o usu�rio � o respons�vel por gerenciar os recursos necess�rios para 
a execu��o das simula��es. Em alguns casos, isso se torna invi�vel, j� que algumas execu��es podem durar v�rias horas, al�m de exigir 
muito da CPU (Central Processing Unit). Para solucionar esse problema a Athena disponibiliza suas funcionalidades como um servi�o acess�vel 
pela Internet. Com isso o usu�rio tem acesso aos sistemas criados e a lista das simula��es executadas, n�o sendo necess�rio consumir os 
recursos da sua pr�pria m�quina.</li>
<li><strong>Performance:</strong> A execu��o de algoritmos de intelig�ncia computacional tem alto custo se levado em considera��o o tempo de CPU e a quantidade 
de mem�ria gastos pela m�quina executante. Para solucionar esse problema a Athena utiliza m�quinas extras presentes em sua infraestrutura e 
sempre que poss�vel os sistemas s�o executados em paralelo para otimizar a performance.</li>
<li><strong>Colabora��o:</strong> A ferramenta quer incentivar a colabora��o entre pesquisadores de diferentes campos da IC, facilitando o desenvolvimento e a 
incorpora��o de novos m�dulos. Al�m de incentivar o uso da ferramenta nas pesquisas cient�ficas.</li>
</ul>

<p class="paragrafo">Atualmente, quando um pesquisador precisa desenvolver uma abordagem para solucionar determinado problema que envolva algoritmos de IC, � 
necess�rio desenhar e implementar os algoritmos utilizados, selecionar um benchmark de problemas apropriado e avaliar a efici�ncia e a 
efic�cia da abordagem para aquele conjunto de dados. Tal avalia��o � feita com a execu��o de diversas simula��es, ajustando os par�metros dos 
algoritmos at� encontrar uma configura��o que gere os resultados mais pr�ximos dos resultados desejados.</p>

<p class="paragrafo">A Athena permite a configura��o de sistemas inteligentes de forma simples, por meio do uso da abstra��o de circuitos l�gicos digitais. 
A Figura 1 apresenta um sistema gerado na ferramenta para uma abordagem de qualifica��o de desenvolvedores com base em tr�s caracter�sticas: 
atitude, habilidade e conhecimento. Essa abordagem foi descrita na se��o 5 do artigo A Hybrid Approach to Solve the Agile Team Allocation Problem (BRITTO et al., 2012). 
Ela utiliza uma t�cnica chamada Matriz de Compet�ncia para gerar as tr�s notas para cada desenvolvedor (atitude, habilidade e conhecimento). 
Essas notas s�o utilizadas como entrada no sistema de infer�ncia Fuzzy, o qual retorna um valor de produtividade associado a cada desenvolvedor.</p>

<br>

<p class="text-center">
	<img alt="" src="${pageContext.request.contextPath}/img/article/athena_system.jpg" style="width: 95%;" class="img-thumbnail">
</p>
<p class="paragrafo text-center">
<strong>
Figura 1: Exemplo de Sistema Computacional criado na Athena.
</strong>
</p>

</div>
