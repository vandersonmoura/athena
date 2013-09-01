<div style="text-align: justify;">
<p class="paragrafo">O objetivo da Athena é possibilitar a criação de sistemas inteligentes híbridos, de maneira simples e transparente. 
Dessa forma a ferramenta pode ser utilizada por pesquisadores, desenvolvedores e usuários que detenham o mínimo de 
conhecimento em técnicas de inteligência computacional.</p>
	
<p class="paragrafo">Para que esse objetivo se concretize, o desenvolvimento da ferramenta foi guiado pelas seguintes metas:</p>

<ul style="margin-right: 10px;">
<li><strong>Simplicidade:</strong> Um dos problemas associado à utilização de técnicas de inteligência computacional está diretamente ligado à 
complexidade de determinados algoritmos. Isso acaba dificultando sua utilização por pesquisadores de áreas não ligadas à computação.</li>
<li><strong>Extensibilidade:</strong> A Athena deve prover uma arquitetura robusta para suportar a complexidade dos algoritmos de IC e ao mesmo tempo 
deve ser flexível para facilitar a inclusão de novos algoritmos e novos componentes.</li>
<li><strong>Software como serviço (SaaS):</strong> Em geral a maioria das ferramentas que visam apoiar técnicas de IC estão disponíveis para download 
e devem ser integradas ao projeto em desenvolvimento. Dessa forma, o usuário é o responsável por gerenciar os recursos necessários para 
a execução das simulações. Em alguns casos, isso se torna inviável, já que algumas execuções podem durar várias horas, além de exigir 
muito da CPU (Central Processing Unit). Para solucionar esse problema a Athena disponibiliza suas funcionalidades como um serviço acessível 
pela Internet. Com isso o usuário tem acesso aos sistemas criados e a lista das simulações executadas, não sendo necessário consumir os 
recursos da sua própria máquina.</li>
<li><strong>Performance:</strong> A execução de algoritmos de inteligência computacional tem alto custo se levado em consideração o tempo de CPU e a quantidade 
de memória gastos pela máquina executante. Para solucionar esse problema a Athena utiliza máquinas extras presentes em sua infraestrutura e 
sempre que possível os sistemas são executados em paralelo para otimizar a performance.</li>
<li><strong>Colaboração:</strong> A ferramenta quer incentivar a colaboração entre pesquisadores de diferentes campos da IC, facilitando o desenvolvimento e a 
incorporação de novos módulos. Além de incentivar o uso da ferramenta nas pesquisas científicas.</li>
</ul>

<p class="paragrafo">Atualmente, quando um pesquisador precisa desenvolver uma abordagem para solucionar determinado problema que envolva algoritmos de IC, é 
necessário desenhar e implementar os algoritmos utilizados, selecionar um benchmark de problemas apropriado e avaliar a eficiência e a 
eficácia da abordagem para aquele conjunto de dados. Tal avaliação é feita com a execução de diversas simulações, ajustando os parâmetros dos 
algoritmos até encontrar uma configuração que gere os resultados mais próximos dos resultados desejados.</p>

<p class="paragrafo">A Athena permite a configuração de sistemas inteligentes de forma simples, por meio do uso da abstração de circuitos lógicos digitais. 
A Figura 1 apresenta um sistema gerado na ferramenta para uma abordagem de qualificação de desenvolvedores com base em três características: 
atitude, habilidade e conhecimento. Essa abordagem foi descrita na seção 5 do artigo A Hybrid Approach to Solve the Agile Team Allocation Problem (BRITTO et al., 2012). 
Ela utiliza uma técnica chamada Matriz de Competência para gerar as três notas para cada desenvolvedor (atitude, habilidade e conhecimento). 
Essas notas são utilizadas como entrada no sistema de inferência Fuzzy, o qual retorna um valor de produtividade associado a cada desenvolvedor.</p>

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
