<form ng-submit="save()">
<input type="text" name="firstname" placeholder="enter first name" ng-model="person.firstName">
<input type="text" name="firstname" placeholder="enter last name" ng-model="person.lastName">
<input type="submit" name="save">
</form>


<table>
<thead>
<tr>
<th>id</th>
<th>firstname</th>
<th>lastname</th>
</tr>
</thead>
<tbody>
<tr ng-repeat="obj in responseDataArray " ><td>{{obj.id}}</td> <td>{{obj.firstname}}</td>  <td>{{obj.lastname}}</td></tr>
</tbody>
</table> 