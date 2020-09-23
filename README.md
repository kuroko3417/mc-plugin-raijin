# mc-plugin-raijin
雷神Plugin




## コマンドリファレンス


ri rs <playerName> or <groupName> ...
  death <true or false or reset>
  power <0 ~ 1000 or reset>
  
|Main|Sub|Option|Desecaption|  
|---|---|---|---|
|ri|||雷神pluginの開始コマンド名です。  |
||pur||スポーン時に雷を落としたいプレイヤーを設定するコマンドです。|  
||obs||Join時に雷を落としたいプレイヤーを設定するコマンドです。|  
||rs||指定したプレイヤーまたはプレイヤーグループに雷を落とします。|  
|||playerNames or groupNames|プレイヤーまたはプレイヤーグループを指定します。（複数指定可能）|
||death||雷を落としたときに確殺するかどうか設定するコマンドです。|  
|||true|雷を落としたときに確殺します。|  
|||false|雷を落としたときに確殺しません。|  
|||reset|設定を初期化します。（初期値:true）|
||power||落雷地点の爆発する威力を設定するコマンドです。|
|||0 ~ 1000|爆発する威力を設定します。|
|||reset|設定を初期化します。（初期値:0）|  
||target||雷を落とすプレイヤーリストを設定するコマンドです。|
|||add|playerName|
|||remove|playerName|
|||list|groupName|
||targets|||プレイヤーリストに関するコマンドです。|
|||delete|groupName|プレイヤーリストを削除します。|
|||clear|groupName|プレイヤーリストをすべて削除します。|
|||list|groupName|プレイヤーリストに所属するプレイヤー一覧を表示します。|
