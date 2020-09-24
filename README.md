# mc-plugin-raijin
雷神Plugin




## コマンドリファレンス

基本構文

`ri <Options...>`

## 主要コマンド

#### ri pur

スポーン時に雷を落としたいプレイヤーを設定するオプションです。  
TETU_tanukiさんのリスキル（煉獄）が元ネタです。

|Command|Options|Desecaption|  
|---|---|---|
|ri pur add|&lt;groupName&gt;|対象のプレイヤーを追加します。|
|ri pur remove|&lt;groupName&gt;|対象からプレイヤを除外します。|
|ri pur list|none|対象のプレイヤー名一覧を表示します。|

備考:オフラインプレイヤーを指定することが可能です。

#### ri obs

Join時に雷を落としたいプレイヤーを設定するコマンドです。  
killコマンド実行するタイミングで上手く逃げたINKYATENSEIを確殺するために作成しました。

|Command|Options|Desecaption|  
|---|---|---|
|ri obs add|&lt;groupName&gt;|対象のプレイヤーを追加します。|
|ri obs remove|&lt;groupName&gt;|対象からプレイヤを除外します。|
|ri obs list|none|対象のプレイヤー名一覧を表示します。|

備考:オフラインプレイヤーを指定することが可能です。

注意:設定対象がJoinして雷が落ちたあと、設定対象から自動的に除外されます。

#### ri rs

指定したプレイヤーまたはプレイヤーグループに雷を落とします。

|Command|Options|Desecaption|  
|---|---|---|
|ri rs|&lt;playerNames... or groupNames...&gt;|指定したプレイヤーまたはプレイヤーグループに雷を落とします。|  

### 設定コマンド

#### ri death

雷を落としたときに確殺するかどうか設定します。

|Command|Options|Desecaption|  
|---|---|---|
|ri death true|none|雷を落としたときに確殺します。|  
|ri death false|none|雷を落としたときに確殺しません。|  
|ri death reset|none|設定を初期化します。（初期値:true）|

#### ri power

雷の落雷地点の爆発威力を設定します。

|Command|Options|Desecaption|  
|---|---|---|
|ri power|0 ~ 1000|爆発する威力を設定します。|
|ri power reset|none|設定を初期化します。（初期値:0）|

#### ri target

プレイヤーリストを設定するコマンドです。

|Command|Options|Desecaption|  
|---|---|---|
|ri target add|&lt;groupName&gt; &lt;playerNames...&gt;|プレイヤーをリストに追加します。|
|ri target remove|&lt;groupName&gt; &lt;playerNames...&gt;|プレイヤーをリストから除外します。|
|ri target list|&lt;groupName&gt;|リストに所属するプレイヤー一覧を表示します。

#### ri targets

プレイヤーリストの設定を制御するコマンドです。

|Command|Options|Desecaption|  
|---|---|---|
|ri targets delete|&lt;groupName&gt;|プレイヤーリストを削除します。|
|ri targets clear|&lt;groupName&gt;|プレイヤーリストをすべて削除します。|
|ri targets list|none|プレイヤーリスト名の一覧を表示します。|
